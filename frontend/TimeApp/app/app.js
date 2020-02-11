'use strict';

// Declare app level module which depends on views, and core components
var myApp = angular.module('myApp', [ 'ngMaterial', 'ngMessages', 'ngRoute', 'ui.router', 'myApp.login', 'myApp.forgotpass', 'myApp.main', 'myApp.tasks']);

myApp.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('purple')
    .accentPalette('teal');
    // .backgroundPalette('#FFFFFF');
});


myApp.config(function($stateProvider) {
  var helloState = {
    name: 'hello',
    url: '/hello',
    template: '<h3>hello world!</h3>'
  }

  var loginState = {
    name: 'login',
    url: '/login',
    templateUrl: '/login/login.html',
    controller: 'loginCtrl'
  }
   var signUpState = {
    name: 'signup',
    url: '/signup',
    templateUrl: '/signup/signup.html'
  }
  var forgotPassState = {
    name: 'forgotpass',
    url: '/forgotpass',
    templateUrl: '/forgotPass/forgotpass.html',
    controller: 'forgotPassCtrl'
  }

  var tasksState = {
    name: 'tasks',
    url: '/tasks',
    templateUrl: '/tasks/tasks.html',
    controller: 'tasksCtrl'
  }

  $stateProvider.state(helloState);
  $stateProvider.state(loginState);
  $stateProvider.state(signUpState);
  $stateProvider.state(forgotPassState);
  $stateProvider.state(tasksState);

});

myApp.factory('loginFactory', function($rootScope) {
  $rootScope.isNotLogged = true;
  var service = {};

  service.isNotLogged = function () {
    return $rootScope.isNotLogged;
  }

  service.setLogIn = function (x) {
    $rootScope.isNotLogged = x;
    console.log("cambio");
  }

  return service;
});
