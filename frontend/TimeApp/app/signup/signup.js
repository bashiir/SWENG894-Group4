'use strict';

angular.module('myApp.signup', ['ui.router', 'ngMessages'])

// .config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/signup', {
//     templateUrl: 'signup/signup.html',
//     controller: 'SignUpCtrl'
//   });
// }])

.controller('SignUpCtrl', function($scope, $http) {
	$scope.user = {
		name: '', password: '', email: '' 
	};
	
	$scope.confirmPass = '';
	
	$scope.submit = function() {
	    console.log('Iam called');

	    $http({
	      method: 'POST',
	      url: 'http://localhost:8001/signup',
	      headers: {
		'Content-Type': 'application/json'
	      },
	      data: JSON.stringify({
		username : $scope.user.name,
		password : $scope.user.password,
		email : $scope.user.email,
		roles : [
		{
		role: "user"

		}
		]
		})
		    }).then(
		      function successCallback(response) {
			console.log(response);
		      },
		      function errorCallback(response) {
			if ((response.status = 401)) {
			  console.log('error :', response.status);
			  console.log(response);
			}
		      }
		 );
	 };
});
