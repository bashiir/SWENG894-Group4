'use strict';

var app = angular.module('myApp.login', ['ui.router']);

app.controller('loginCtrl', function($scope, $state) {
  $scope.user = {
		name: '', password: ''  
	}

	$scope.removeLogin = function() {
    $scope.loginEnable = false;
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



