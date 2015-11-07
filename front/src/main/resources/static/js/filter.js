var app = angular.module('biosignals-app', []);

app.controller('FilterController', function($scope, $http) {

    $scope.loadMetadata = function() {
        $http.get('/visualize/metadata').
              success(function(data, status, headers, config) {
                    $scope.edfMetadata = data;
                    $scope.signalIndexes = Array.apply(null, Array(data.signalMetadata.length)).map(function (_, i) {return i;})
              }).
              error(function(data, status, headers, config) {
                    alert("Failed to get metadata");
              });
    };

    $scope.recordNo = 0;
    $scope.zoom = 1;
    $scope.positionInRecord = 0;
    $scope.playing = false;
    $scope.interval = null;
    $scope.showGraphs = false;

    $scope.visualize = function() {
        if ($scope.selectedSignal && $scope.selectedFilter) {
            visualizeSignalRecord($http, $scope, $scope.recordNo, $scope.selectedSignal, $scope.zoom, $scope.positionInRecord, $scope.selectedFilter);
            $scope.showGraphs = true;
        }
    };


    $scope.selectSignal = function() {
        var signal = document.getElementById("selectedSignal");
        $scope.selectedSignal = signal.value;
    };

    $scope.selectFilter = function() {
        var filter = document.getElementById("selectedFilter");
        $scope.selectedFilter = filter.value;
    };

    $scope.next = function() {
        if ($scope.zoom >= 1) {
            $scope.recordNo = $scope.recordNo + $scope.zoom;
            $scope.positionInRecord = 0;
        } else if ($scope.positionInRecord < (1/$scope.zoom) - 1){
            $scope.positionInRecord = $scope.positionInRecord + 1;
        } else {
            $scope.positionInRecord = 0;
            $scope.recordNo = $scope.recordNo + 1;
        }
    };

    $scope.previous = function() {
        if ($scope.zoom >= 1) {
                $scope.recordNo = $scope.recordNo - $scope.zoom;
                $scope.positionInRecord = 0;


        } else if ($scope.positionInRecord > 0){
            $scope.positionInRecord = $scope.positionInRecord - 1;
        } else {

                $scope.positionInRecord = (1/$scope.zoom) - 1;
                $scope.recordNo = $scope.recordNo - 1;

        }
    };

    $scope.zoomIn = function() {
        $scope.zoom = $scope.zoom / 2;
        $scope.positionInRecord = $scope.positionInRecord * 2;
    };

    $scope.zoomOut = function() {
        $scope.zoom = $scope.zoom * 2;
        if ($scope.zoom >= 1){
            $scope.positionInRecord = 0;
        } else {
            $scope.positionInRecord = Math.floor($scope.positionInRecord / 2);
        }
    };

    $scope.playForth = function() {
        if  (!$scope.playing){
            $scope.interval = setInterval(
            function(){
                $scope.next();
                $scope.visualize();
            }, 2000);
            $scope.playing = true;
        }
    };

    $scope.playBack = function() {
        if  (!$scope.playing){
            $scope.interval = setInterval(
            function(){
                $scope.previous();
                $scope.visualize();
            }, 2000);
            $scope.playing = true;
        }
    };

    $scope.stop = function() {
        if ($scope.playing){
            clearInterval($scope.interval);
            $scope.playing = false;
        }
    };

    $scope.goToRecord = function(keyEvent) {
        if (keyEvent.which === 13) {
            if ($scope.playing){
                $scope.stop();
            }
            var record = document.getElementById("recordNoInput");
            $scope.recordNo = parseInt(record.value) - 1;
            $scope.positionInRecord = 0;
            $scope.visualize();
        }

    };


});

function visualizeSignalRecord($http, $scope, recordNo, signalNo, zoom, positionInRecord, filter) {

    paramsFilter = {
        recordNo: recordNo,
        signalNo: signalNo,
        zoom: zoom,
        positionInRecord: positionInRecord,
        filterType: filter
    };

    $http({
        url: '/filter/data',
        method: 'GET',
        params: paramsFilter}).
    success(function(data, status, headers, config) {
            $scope.signalData = data;
            drawChart($scope.signalData, "My first chart", 2);

    }).
    error(function(data, status, headers, config) {
          alert("failed " + status + " " + data);
    });

    paramsOriginal = {
            recordNo: recordNo,
            signalNo: signalNo,
            zoom: zoom,
            positionInRecord: positionInRecord
        };

        $http({
            url: '/visualize/data',
            method: 'GET',
            params: paramsOriginal}).
        success(function(data, status, headers, config) {
                $scope.signalData = data;
                drawChart($scope.signalData, "My first chart", 1);

        }).
        error(function(data, status, headers, config) {
              alert("failed " + status + " " + data);
        });
};


function drawChart(timeSeries, title, signalNo) {

       var ctx = document.getElementById("chart" + signalNo).getContext("2d");

        var a = timeSeries.slice();
        var labels = [];

        for (i = 0; i < timeSeries.length; i++){
            labels.push("");
        }


        var dataForChart = {
            labels: labels,
            datasets: [
                {
                    label: title,
                    strokeColor: "#2466A8",
                    data: timeSeries
                }
            ]
        };

        var myLineChart = new Chart(ctx).Line(dataForChart,
            {responsive: true, bezierCurve : false, pointDot : false, showScale:false, datasetStrokeWidth: 0.8, datasetFill : false, animation: false, showTooltips: false}
        );

}

