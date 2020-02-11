'use strict';

var app = angular.module('myApp.tasks', ['ui.router']);

app.controller('tasksCtrl', function($scope, $state, $log, $mdDialog, timeFactory) {
  	$scope.tsk = {
  		eTime: "02:00 PM",
  		eDate: new Date(),
  		sTime: "01:00 PM",
  		sDate: new Date()
  	}

  	$scope.categories = ['Test', 'TEST2'];

  	$scope.times = timeFactory.getTimes();

  	$scope.addCatg = true;

  	$scope.myDate = new Date();

    this.onDateChanged = function() {
      $log.log('Updated Date: ', this.myDate);
    };

     $scope.addCategory = function(d) {
     	$scope.addCatg = d;
 	};

 	 $scope.showConfirm = function(ev, i) {
    // Appending dialog to document.body to cover sidenav in docs app
    var confirm = $mdDialog.confirm()
          .title('Are you sure you want to delete the task?')
          .textContent('The selected task ' + i + ' will be deleted from the system.')
          .ariaLabel('Delete Task')
          .targetEvent(ev)
          .ok('Confirm')
          .cancel('Cancel');

    $mdDialog.show(confirm).then(function() {
      $scope.status = 'You decided delete the task.';
    }, function() {
      $scope.status = 'You decided to keep the task.';
    });
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

	$scope.showEdit = function(ev) {
	    $mdDialog.show({
	      controller: DialogController,
	      templateUrl: 'tasks/dialog2.tmpl.html',
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

	// $scope.showPrompt = function(ev) {
 //    // Appending dialog to document.body to cover sidenav in docs app
 //    var confirm = $mdDialog.prompt()
 //      .title('What would you name your dog?')
 //      .textContent('Bowser is a common name.')
 //      .placeholder('Dog name')
 //      .ariaLabel('Dog name')
 //      .initialValue('Buddy')
 //      .targetEvent(ev)
 //      .required(true)
 //      .ok('Okay!')
 //      .cancel('I\'m a cat person');

 //    $mdDialog.show(confirm).then(function(result) {
 //      $scope.status = 'You decided to name your dog ' + result + '.';
 //    }, function() {
 //      $scope.status = 'You didn\'t name your dog.';
 //    });
 //  };

});

// app.config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/login', {
//     templateUrl: '/login/login.html',
//     controller: 'loginCtrl'
//   });

// }]);
