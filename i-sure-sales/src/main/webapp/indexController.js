/**
 * Created by milica.govedarica on 12/14/2017.
 */
(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('indexController', function ($scope, ngNotify, $state, $http, insuranceService) {
        var indexVm = this;

        indexVm.getInsurance = function() {
            $state.go('insurancePage');
        }
        
        indexVm.email = "http://localhost:80/squirrelmail/src/login.php";
        
        indexVm.logout = function(){
        	insuranceService.logout().then(
          		  function (response) {
          			  console.log("logout");
          	  });
        }

    });

})();