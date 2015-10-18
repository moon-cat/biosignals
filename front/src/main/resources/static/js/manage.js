var app = angular.module('biosignals-app', []);

app.controller('ManageController', function($scope, $http, $location) {

    $scope.list = function(){
        list($http, $scope);
    };

    $scope.remove = function(uuid){
        remove($http, $scope, uuid);
        window.location.href = "/html/manage.html";
    };

    $scope.analyze = function(uuid){
        analyze($http, $scope, uuid);
    };

    $scope.visualize = function(uuid){
        visualize($http, $scope, $location, uuid);
    };
});


function list($http, $scope) {
    $http({
        url: '/manage/list',
        method: 'GET',
        params: {}}).
    success(function(data, status, headers, config) {
            $scope.edfProperties = data;
    }).
    error(function(data, status, headers, config) {
          alert("failed " + status + " " + data);
    });
};

function visualize($http, $scope, $location, uuid) {
    $http({
        url: '/select',
        method: 'POST',
        params: {uuid: uuid}}).
    success(function(data, status, headers, config) {
        window.location.href = "/html/visualize.html";
    }).
    error(function(data, status, headers, config) {
          alert("failed " + status + " " + data);
    });
};

function analyze($http, $scope, uuid) {
    window.location.href = "/html/analyze.html";
};


function remove($http, $scope, uuid) {
    $http({
        url: '/manage/remove',
        method: 'POST',
        params: {uuid : uuid}}).
    success(function(data, status, headers, config) {
            $scope.edfProperties = data;
    }).
    error(function(data, status, headers, config) {
          alert("failed " + status + " " + data);
    });
};



