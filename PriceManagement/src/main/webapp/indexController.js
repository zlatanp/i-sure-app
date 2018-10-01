/**
 * Created by desanka on 1/4/2018.
 */
(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('indexController', function ($scope, ngNotify, $state, $http, mainService, $route, $window) {
        var indexVm = this;
        indexVm.riskTypes = [];
        indexVm.currentlyActivePricelist = null;
        indexVm.allRisks = [];
        indexVm.residualRisks = [];
        indexVm.maxDateTo = null;
        indexVm.allRiskForRiskType = [];
        indexVm.showTable = false;
        
        indexVm.risk = {
      	      riskName: '',
      	      riskType: ''
      	    };
      
      indexVm.pricelistItems = [];
      
      
      indexVm.pricelist = {
    		id: 0,
      		dateFrom: '',
      		dateTo: '',
      		pricelistItems : indexVm.pricelistItems
      }
      
      
      indexVm.email = "http://localhost:80/squirrelmail/src/login.php";
      
      indexVm.addRisk = false;
      indexVm.addPricelist = false;
      indexVm.addRule = false;
      
      indexVm.ruleTextarea = "";
      
      indexVm.logout = function(){
    	  mainService.logout().then(
          		  function (response) {
          			  console.log("logout");
          	  });
      }
      
      indexVm.loadDrl = function(){
    	  mainService.openFile().then(
    		  function (response) {
    			  indexVm.ruleTextarea = response.data.content;
    	  });
      }
      
      indexVm.editRule = function(){
    	  var file = {"content": indexVm.ruleTextarea};
    	  
    	  mainService.saveFile(file).then(
        		  function (response) {
        			  toastr.success("Rule is added.",'<i>Success</i>');
        	  });
      }
      
        
        init();

        function init() {
        	mainService.getRiskTypes().then(
                function (response) {
                    if (response.status == 200) {
                    	indexVm.riskTypes = response.data;
                    }
                });
        	
        	mainService.findCurrentlyActive().then(
        			function (response) {
                        if (response.status == 200) {
                        	if(response.data == ""){
                        		indexVm.currentlyActivePricelist = null;
                        		indexVm.pricelistItems = [];
                        	}else{
                        		indexVm.currentlyActivePricelist = response.data;
                        		indexVm.pricelist.id = indexVm.currentlyActivePricelist.id;
                        		indexVm.pricelistItems = indexVm.currentlyActivePricelist.pricelistItems;
                        		mainService.findMaxDateTo().then(
                            			function (response) {
                                            if (response.status == 200) {
                                            	indexVm.maxDateTo = new Date(response.data);
                                            	indexVm.maxDateTo.setDate(indexVm.maxDateTo.getDate() + 1);
                                            }
                            			});
                        	}
                        }else{
                        	indexVm.currentlyActivePricelist = null;
                        	indexVm.pricelistItems = [];
                        	indexVm.maxDateTo = new Date();
                        }
                        
                    });
        }
        
        indexVm.showRiskForm = function(){
        	 indexVm.risk = {
             	      riskName: '',
             	      riskType: ''
             	    };
             
        	indexVm.addRisk = true;
        	indexVm.addPricelist = false;
        	indexVm.addRule = false;
        	indexVm.showTable = false;
        }
        
        indexVm.showPricelistForm = function(){
        	indexVm.addPricelist = true;
        	indexVm.addRisk = false;
        	indexVm.addRule = false;
        	indexVm.showTable = false;
        	mainService.getRisks().then(
                function (response) {
                    if (response.status == 200) {
                    	indexVm.allRisks = response.data;
                    	if(indexVm.currentlyActivePricelist == null){
                    		indexVm.addNew();
                    	}
                    	residualRisks();
                    }
                });
        }
        
        indexVm.showRuleForm = function(){
        	indexVm.addRule = true;
        	indexVm.addPricelist = false;
        	indexVm.addRisk = false;
        	indexVm.showTable = false;
        	indexVm.loadDrl();
        }
        
        indexVm.submitAddingRisk = function(){
        	mainService.createRisk(indexVm.risk).then(
                function (response) {
                    if (response.status == 200) {
                    	toastr.success("Risk is added.",'<i>Success</i>');
                    	indexVm.allRiskForRiskType = response.data;
                    	indexVm.addRisk = false;
                    	indexVm.showTable = true;
                	}
            });
        }

        indexVm.isDateValid = function (date) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            var d = new Date(date);
            d.setHours(0, 0, 0, 0);
            return d >= today;
        };
        
        function residualRisks(){
        	var risksWithPrice = [];
        	var allRisks = indexVm.allRisks;
        	angular.forEach(indexVm.pricelistItems, function(pricelistItem) {
            	risksWithPrice.push(pricelistItem.risk);
            });
        	if(risksWithPrice.length != 0){
        		indexVm.residualRisks = indexVm.allRisks;
	        	for(var i=0;i<allRisks.length;i++){
	        		for(var j=0;j<risksWithPrice.length;j++){
	        			if(risksWithPrice[j] != null){
		        			if(allRisks[i].id == risksWithPrice[j].id){
		        				var index = indexVm.residualRisks.indexOf(allRisks[i]);
		        				indexVm.residualRisks.splice(index, 1);
		        			}
	        			}
        			}
	        	}
        	}else{
        		indexVm.residualRisks = indexVm.allRisks;
        	}
        	console.log(indexVm.residualRisks)
        }
        
        indexVm.addNew = function(){
        	residualRisks();
        	indexVm.pricelistItems.push({ 
                	coefficient : '',
            		price: '',
            		risk: indexVm.residualRisks[0]
                });
        	
        };
        
        indexVm.remove = function(){
            var newDataList=[];
            $scope.selectedAll = false;
            angular.forEach(indexVm.pricelistItems, function(selected){
                if(!selected.selected){
                    newDataList.push(selected);
                }
            }); 
            indexVm.pricelistItems = newDataList;
            residualRisks();
        };
        
        indexVm.checkAll = function () {
            if (!$scope.selectedAll) {
                $scope.selectedAll = true;
            } else {
                $scope.selectedAll = false;
            }
            angular.forEach(indexVm.pricelistItems, function(pricelistItem) {
            	pricelistItem.selected = $scope.selectedAll;
            });
        }; 
        
        function createPatternOfDate(date){
        	var now = new Date(); // for now
        	now.getHours();
        	now.getMinutes();
            var pattern = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()+" "+now.getHours()+":"+now.getMinutes();
            return pattern;
        }
        
        indexVm.submitAddingPricelist = function(){
        	indexVm.pricelist.dateTo = createPatternOfDate(indexVm.pricelist.dateTo);
        	indexVm.pricelist.pricelistItems = indexVm.pricelistItems;
        	mainService.createPricelist(indexVm.pricelist).then(
                function (response) {
                	
                    if (response.status == 201) {
                    	toastr.success("Pricelist is added.",'<i>Success</i>');
                    	
                    	mainService.findMaxDateTo().then(
                    			function (response) {
                                    if (response.status == 200) {
                                    	indexVm.maxDateTo = new Date(response.data);
                                    	indexVm.maxDateTo.setDate(indexVm.maxDateTo.getDate() + 1);
                                    }
                    			});
                	}else{
                		toastr.error("Something got wrong with pricelist. Try again.",'<i>Error</i>');
                	}
                    setTimeout(function(){
                	    location.reload();
                	},5000);
            });
        	
        }

    });

})();