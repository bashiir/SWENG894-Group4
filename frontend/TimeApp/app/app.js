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

  var aboutState = {
    name: 'login',
    url: '/login',
    templateUrl: '/login/login.html'
  }

  $stateProvider.state(helloState);
  $stateProvider.state(aboutState);
});


