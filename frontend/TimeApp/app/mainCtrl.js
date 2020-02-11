'use strict';

angular.module('myApp.main', ['ui.router'])


.controller('mainCtrl', function($scope, $rootScope, $timeout,$mdSidenav, $log, $state,loginFactory) {

	$rootScope.isNotLogged = loginFactory.isNotLogged();

	    $scope.toggleLeft = buildDelayedToggler('left');


	$scope.logOut = function() {
    loginFactory.setLogIn(true);
    // $scope.loginEnable = false;
    $state.go('login');
  }

 $scope.close = function () {
      // Component lookup should always be available since we are not using `ng-if`
      $mdSidenav('left').close()
        .then(function () {
          $log.debug("close LEFT is done");
        });
    }

function debounce(func, wait, context) {
      var timer;

      return function debounced() {
        var context = $scope,
            args = Array.prototype.slice.call(arguments);
        $timeout.cancel(timer);
        timer = $timeout(function() {
          timer = undefined;
          func.apply(context, args);
        }, wait || 10);
      };
    }

    function buildDelayedToggler(navID) {
      return debounce(function() {
        // Component lookup should always be available since we are not using `ng-if`
        $mdSidenav(navID)
          .toggle()
          .then(function () {
            $log.debug("toggle " + navID + " is done");
          });
      }, 200);
    }

});
