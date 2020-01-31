'use strict';

angular.module('myApp.signup', ['ngRoute', 'ngMessages'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/signup', {
    templateUrl: 'signup/signup.html',
    controller: 'SignUpCtrl'
  });
}])

.controller('SignUpCtrl', [function() {
	$scope.user = {
		name: '', password: '', email: '' 
	};
}]);