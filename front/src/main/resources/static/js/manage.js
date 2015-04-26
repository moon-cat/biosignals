var app = angular.module('biosignals-app', []);

app.controller('ManageController', function($scope, $http) {

    $scope.list = function(){
        list($http, $scope);
    };

    $scope.remove = function(uuid){
        remove($http, $scope, uuid);
        list($http, $scope);
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



