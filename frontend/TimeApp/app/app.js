'use strict';

// Declare app level module which depends on views, and core components
var myApp = angular.module('myApp', [ 'ngMaterial', 'ngMessages', 'ngRoute', 'ui.router', 'myApp.login', 'myApp.forgotpass', 'myApp.signup']);

myApp.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('purple')
    .accentPalette('teal');
    // .backgroundPalette('#FFFFFF');
});

myApp.factory('loginService', function() {
  var loginEnable = true;

  return loginEnable;
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
    templateUrl: '/signup/signup.html',
    controller: 'SignUpCtrl'
  }
  var forgotPassState = {
    name: 'forgotpass',
    url: '/forgotpass',
    templateUrl: '/forgotPass/forgotpass.html',
    controller: 'forgotPassCtrl'
  }

  $stateProvider.state(helloState);
  $stateProvider.state(loginState);
  $stateProvider.state(signUpState);
  $stateProvider.state(forgotPassState);

});


