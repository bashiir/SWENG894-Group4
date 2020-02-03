'use strict';

var app = angular.module('myApp.login', ['ui.router']);

app.controller('loginCtrl', function($scope, $state, loginFactory, $http) {
  $scope.user = {
		name: '', password: ''  
  }
  var iservice = {};
	$scope.setLogIn = function(x) {
    loginFactory.setLogIn(x);
    // $scope.loginEnable = false;
    // $state.go('signup');
  }

  $scope.submit = function() {
    console.log('Iam called');
    iservice.login()
  };

 
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
 //'localhost:8001/dashboard/',

  iservice.login = function(){
    var req = {
      method: 'GET',
      url: 'http://google.com',
   /**    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'basic' + $scope.user.name + ':' + $scope.user.password
      },
      //data: {  }
      */
     }

    $http(req)
    .then(function(response) {
      console.log(response);
    }).catch(function(err){
      console.log("error occured I am in catch block help!",err.error)
    });
  }



	
});

// app.config(['$routeProvider', function($routeProvider) {
//   $routeProvider.when('/login', {
//     templateUrl: '/login/login.html',
//     controller: 'loginCtrl'
//   });

// }]);



