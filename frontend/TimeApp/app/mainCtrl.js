'use strict';

angular.module('myApp.main', ['ui.router'])


.controller('mainCtrl', function($scope, $rootScope, loginFactory) {
	
	$rootScope.isNotLogged = loginFactory.isNotLogged();

	$scope.logOut = function() {
    loginFactory.setLogIn(true);
    // $scope.loginEnable = false;
    // $state.go('signup');
  }
});