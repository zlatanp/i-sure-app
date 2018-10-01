/**
 * Created by milica.govedarica on 12/14/2017.
 */
(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('indexController', function ($scope, ngNotify, $state) {
        var indexVm = this;

        indexVm.getInsurance = function() {
            $state.go('insurancePage');
        }
    });

})();