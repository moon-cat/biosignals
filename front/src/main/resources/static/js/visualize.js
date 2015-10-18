var app = angular.module('biosignals-app', []);

app.controller('VisualizeController', function($scope, $http) {


    $scope.loadMetadata = function() {
        $http.get('/visualize/metadata').
              success(function(data, status, headers, config) {
                    $scope.edfMetadata = data;
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

    $scope.visualize = function() {
        for (i = 0; i < $scope.selectedSignals.length; i++){
            visualizeSignalRecord($http, $scope, $scope.recordNo, $scope.selectedSignals[i], $scope.zoom, $scope.positionInRecord);
        }
    };


    $scope.selectSignals = function() {
        var signalCheckboxes = document.getElementsByClassName("signal-checkbox");
        $scope.selectedSignals = [];

        for (i = 0; i < signalCheckboxes.length; i++){
            if (signalCheckboxes[i].checked == true) {
                $scope.selectedSignals.push(i);
            }
        }

        $scope.visualize();
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

function visualizeSignalRecord($http, $scope, recordNo, signalNo, zoom, positionInRecord) {

    var params = {
        recordNo: recordNo,
        signalNo: signalNo,
        zoom: zoom,
        positionInRecord: positionInRecord
    };

    $http({
        url: '/visualize/data',
        method: 'GET',
        params: params}).
    success(function(data, status, headers, config) {
            $scope.signalData = data;
            drawChart($scope.signalData, "My first chart", signalNo);

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

