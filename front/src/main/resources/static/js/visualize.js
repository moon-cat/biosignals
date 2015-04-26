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

    $scope.visualize = function() {

        for (i = 0; i < $scope.selectedSignals.length; i++){
            visualizeSignalRecord($http, $scope, $scope.recordNo, $scope.selectedSignals[i]);
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
});

function visualizeSignalRecord($http, $scope, recordNo, signalNo) {

    var params = {
        recordNo: recordNo,
        signalNo: signalNo
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
            {bezierCurve : false, pointDot : false, datasetFill : false, scaleShowGridLines : true, animation: false, showTooltips: false,
            scaleShowVerticalLines: true,scaleShowHorizontalLines: true}
        );

}
