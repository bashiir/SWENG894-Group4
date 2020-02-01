'use strict';

angular.module('myApp.main', ['ui.router'])


.controller('mainCtrl', function($scope) {
	$scope.loginEnable = true;
	
	$scope.removeLogin = function() {
    $scope.loginEnable = false;
  };

	
});