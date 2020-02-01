'use strict';

describe('myApp.forgotpass module', function() {

  beforeEach(module('myApp.forgotpass'));

  describe('forgotpass controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      debugger;
      var forgotpassCtrl = $controller('forgotpassCtrl');
      expect(forgotpassCtrl).toBeDefined();
    }));

  });

  describe('$scope.grade', function() {
    it('sets the strength to "strong" if the password length is >8 chars', function() {
      var $scope = $rootScope.$new();
      var controller = $controller('loginCtrl', { $scope: $scope });
      $scope.password = 'longerthaneightchars';
      $scope.grade();
      expect($scope.strength).toEqual('strong');
    });
  });
});