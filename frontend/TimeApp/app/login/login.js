'use strict';

var app = angular.module('myApp.login', ['ui.router']);

app.controller('loginCtrl', function($scope, $state, loginFactory) {
  $scope.user = {
		name: '', password: ''  
	}

	$scope.setLogIn = function(x) {
    loginFactory.setLogIn(x);
    // $scope.loginEnable = false;
    // $state.go('signup');
  }

  // function($scope, notify) {
  //  $scope.callNotify = function(msg) {
  //    notify(msg);
  //  };
	
});

// app.config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/login', {
//     templateUrl: '/login/login.html',
//     controller: 'loginCtrl'
//   });

// }]);



