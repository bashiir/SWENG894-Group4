var app = angular.module('myApp.login');

app.factory('authService', [
  '$http',
  function($http) {
    var service = {
      login: login
    };
    service.Login = function(username, password, callback) {
      // callback('service is called');


    };
  }
]);
