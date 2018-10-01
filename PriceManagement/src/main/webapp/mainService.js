/**
 * Created by desanka on 1/4/2018.
 */
(function() {
    'use strict';
    angular.module('iSure').factory('mainService', function mainService($http) {

    	mainService.getRiskTypes = function() {
            return $http.get("riskTypes");
            }
    	
    	mainService.getRisks = function() {
            return $http.get("risks");
            }
        
    	mainService.createRisk = function(riskDTO) {
            return $http.post("risks",riskDTO);
            }

    	mainService.findCurrentlyActive = function() {
            return $http.get("pricelists/currentlyActive");
            }
    	
    	mainService.createPricelist = function(pricelistDTO) {
            return $http.post("pricelists",pricelistDTO);
            }
    	
    	mainService.getPricelistById = function(id) {
            return $http.get("pricelistItems/"+id);
            }
    	
    	mainService.findMaxDateTo = function() {
            return $http.get("pricelists/maxDateTo");
            }
    	
    	mainService.openFile = function(){
    		return $http.get("/openFile");
    	}
    	
    	mainService.saveFile = function(content){
    		return $http.post("/saveFile", content);
    	}
    	
    	mainService.logout = function () {
            return $http.get("/logout");
        }
        return mainService;

    });
})();