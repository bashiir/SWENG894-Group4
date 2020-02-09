'use strict';

var app = angular.module('myApp.tasks', ['ui.router']);

app.controller('tasksCtrl', function($scope, $state, $log, $mdDialog) {
  	this.myDate = new Date();

    this.onDateChanged = function() {
      $log.log('Updated Date: ', this.myDate);
    };

    $scope.showAdvanced = function(ev) {
	    $mdDialog.show({
	      controller: DialogController,
	      templateUrl: 'tasks/dialog1.tmpl.html',
	      parent: angular.element(document.body),
	      targetEvent: ev,
	      clickOutsideToClose:true,
	      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
	    })
	    .then(function(answer) {
	      $scope.status = 'You said the information was "' + answer + '".';
	    }, function() {
	      $scope.status = 'You cancelled the dialog.';
	    });
	};

	function DialogController($scope, $mdDialog) {
    $scope.hide = function() {
      $mdDialog.hide();
    };

    $scope.cancel = function() {
      $mdDialog.cancel();
    };

    $scope.answer = function(answer) {
      $mdDialog.hide(answer);
    };
	}	
});

// app.config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/login', {
//     templateUrl: '/login/login.html',
//     controller: 'loginCtrl'
//   });

// }]);



