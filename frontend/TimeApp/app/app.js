'use strict';

// Declare app level module which depends on views, and core components
var myApp = angular.module('myApp', [ 'ngMaterial', 'ngMessages', 'ui.router']);

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
    templateUrl: '/login/login.html'
  }
   var signUpState = {
    name: 'signup',
    url: '/signup',
    templateUrl: '/signup/signup.html'
  }

  $stateProvider.state(helloState);
  $stateProvider.state(loginState);
  $stateProvider.state(signUpState);
});


