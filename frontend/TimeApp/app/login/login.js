'use strict';

var app = angular.module('myApp.login', ['ui.router']);

app.controller('loginCtrl', function($scope, $state, loginFactory, authService) {
  $scope.user = {
		name: '', password: ''  
	}

	$scope.setLogIn = function(x) {
    loginFactory.setLogIn(x);
    // $scope.loginEnable = false;
    // $state.go('signup');
  }

  $scope.submit = function() {
    console.log('Iam called');
    /**   $http({
      method: 'POST',
      url: 'http://localhost:8001/signup/',
      headers: {
        Authorization:
          'Basic ' + $scope.user.name + ':' + $scope.user.password
      },
      data: ''
    }).then(
      function successCallback(response) {
        console.log(response);
      },
      function errorCallback(response) {
        if ((response.status = 401)) {
          console.log('error :', response.status);
        }
      }
    );
    */
  };

	
});

// app.config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/login', {
//     templateUrl: '/login/login.html',
//     controller: 'loginCtrl'
//   });

// }]);



