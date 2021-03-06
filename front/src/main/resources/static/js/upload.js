var app = angular.module('biosignals-app', []);

app.controller('UploadController', function($scope, $http) {

    $scope.upload = function() {

            var fd = new FormData();
            fd.append('name', document.getElementById('name').value);
            fd.append('file', document.getElementById('file').files[0]);

            result = $http.post("/upload/data", fd, {
                headers: {'Content-Type': undefined}
            }).success(function(data, status, headers, config){
                $scope.edfMetadata = data;
                //alert(angular.toJson($scope.edf.edfProperties));
            }).error(function(data, status, headers, config){
                alert("failed " + status + " " + data);
            });
        };

    }
);
