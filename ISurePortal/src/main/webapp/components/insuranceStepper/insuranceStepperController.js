(function () {

    'use strict';
    var app = angular.module('iSure');

    app.controller('insuranceStepperController', function ($scope, insuranceService, ngNotify, $state, $mdDialog) {

        var vm = this;

        init();

        function init() {
            vm.selectedStep = 0;
            vm.maxStep = 6;
            vm.showBusyText = false;
            vm.tabs = [];
            vm.dummy = {};
            vm.prices = [];
            vm.totalPrice = 0;
            vm.pay = 'paypal';


            vm.stepTwo = {
                completed: false, optional: false,
                data: {}
            };

            insuranceService.getTravelInsuranceRisks("International Travel").then(
                function (response) {
                    if (response.status == 200) {
                        vm.travelRisks = response.data;
                        vm.stepOne = {
                            completed: false, optional: false, data: {
                                selectedRegion: vm.travelRisks['Region'][0],
                                numberOfPeople: {},
                                selectedSport: vm.travelRisks['Sport'][0],
                                selectedAmount: vm.travelRisks['Value'][0]
                            }
                        };
                        vm.tabs.push("" + 1);
                    }
                });

            insuranceService.getTravelInsuranceRisks("Home").then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        vm.homeRisks = response.data;
                        vm.stepThree = {
                            completed: false, optional: true, isSkiped: true,
                            data: {
                                selectedArea: vm.homeRisks['Surface area'][0],
                                selectedAge: vm.homeRisks['Property age'][0],
                                selectedValue: vm.homeRisks['Estimated value of property'][0],
                                selectedRisk: vm.homeRisks['Property risks'][0]
                            }
                        };
                    }
                });

            insuranceService.getTravelInsuranceRisks("Roadside").then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        vm.carRisks = response.data;
                        vm.stepFour = {
                            completed: false, optional: true, isSkiped: true,
                            data: {
                                selectedAccommodation: vm.carRisks['Accommodation'][0],
                                selectedRepair: vm.carRisks['Repair'][0],
                                selectedTowing: vm.carRisks['Towing'][0],
                                selectedTransport: vm.carRisks['Transport'][0]
                            }
                        };
                    }
                });
        }

        vm.moveToPreviousStep = function moveToPreviousStep() {
            if (vm.selectedStep > 0) {
                vm.selectedStep = vm.selectedStep - 1;
            }
        };

        vm.calculateDays = function calculateDays(fromDate, toDate) {
            var timeDiff = Math.abs(fromDate.getTime() - toDate.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
            return diffDays;
        };

        vm.submitCurrentStep = function submitCurrentStep() {
            switch (vm.selectedStep) {
                case 0: {
                    vm.addTabs();
                }
                    break;
                case 2:
                    vm.stepThree.isSkiped = false;
                    break;
                case 3:
                    vm.stepFour.isSkiped = false;
                    break;
            }
            vm.selectedStep = vm.selectedStep + 1;
            if (vm.selectedStep == 4) {//if click proceed on car insurance
                vm.getPrice();
            }

        };

        vm.getPrice = function () {
            var internationalTravelInsuranceDTO = createInternationalTravelInsuranceDTO();
            console.log(internationalTravelInsuranceDTO);

            var homeInsuranceDTO = null;
            if (!vm.stepThree.isSkiped) {
                homeInsuranceDTO = createHomeInsuranceDTO();
            }

            console.log(homeInsuranceDTO);

            var roadsideAssistanceInsuranceDTO = null;
            if (!vm.stepFour.isSkiped) {
                roadsideAssistanceInsuranceDTO = createRoadsideAssistanceInsuranceDTO();
            }

            console.log(roadsideAssistanceInsuranceDTO);
            var insurancePolicyDTO =
                {
                    "totalPrice": 1,
                    "dateOfIssue": vm.todayDate,
                    "dateBecomeEffective": vm.stepOne.data.fromDate,
                    "customers": null,
                    "internationalTravelInsurance": internationalTravelInsuranceDTO,
                    "homeInsurance": homeInsuranceDTO,
                    "roadsideAssistanceInsurance": roadsideAssistanceInsuranceDTO
                }
            console.log(insurancePolicyDTO);
            insuranceService.getPrice(insurancePolicyDTO).then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        vm.prices = response.data;
                        vm.totalPrice = vm.prices[0] + vm.prices[1] + vm.prices[2];
                    }
                });

        }

        function createInternationalTravelInsuranceDTO() {
            var travelRisks = [];
            travelRisks.push(vm.stepOne.data.selectedRegion);
            if (vm.playSport) {
                travelRisks.push(vm.stepOne.data.selectedSport);
            }
            travelRisks.push(vm.stepOne.data.selectedAmount);

            var internationalTravelInsuranceDTO = {
                "startDate": createPatternOfDate(vm.stepOne.data.fromDate),
                "endDate": createPatternOfDate(vm.stepOne.data.toDate),
                "durationInDays": vm.calculateDays(vm.stepOne.data.fromDate, vm.stepOne.data.toDate),
                "numberOfPersons": sumNumberOfPeople(),
                "price": 0,
                "risks": travelRisks
            }

            return internationalTravelInsuranceDTO;
        }

        function createHomeInsuranceDTO() {
            var homeRisks = [];
            homeRisks.push(vm.stepThree.data.selectedArea);
            homeRisks.push(vm.stepThree.data.selectedAge);
            homeRisks.push(vm.stepThree.data.selectedRisk);
            homeRisks.push(vm.stepThree.data.selectedValue);

            var homeInsuranceDTO =
                {
                    "ownerFirstName": vm.stepThree.data.firstname,
                    "ownerLastName": vm.stepThree.data.lastname,
                    "address": vm.stepThree.data.address,
                    "personalId": vm.stepThree.data.personalId,
                    "price": 0,
                    "risks": homeRisks
                }
            return homeInsuranceDTO;
        }

        function createRoadsideAssistanceInsuranceDTO() {
            var roadsideRisks = [];
            roadsideRisks.push(vm.stepFour.data.selectedAccommodation);
            roadsideRisks.push(vm.stepFour.data.selectedRepair);
            roadsideRisks.push(vm.stepFour.data.selectedTowing);
            roadsideRisks.push(vm.stepFour.data.selectedTransport);

            var roadsideAssistanceInsuranceDTO =
                {
                    "ownerFirstName": vm.stepFour.data.ownerFirstname,
                    "ownerLastName": vm.stepFour.data.ownerLastname,
                    "personalId": vm.stepFour.data.personalId,
                    "carBrand": vm.stepFour.data.carBrand,
                    "carType": vm.stepFour.data.carType,
                    "yearOfManufacture": vm.stepFour.data.yearOfManufacture,
                    "licencePlateNumber": vm.stepFour.data.licencePlateNumber,
                    "undercarriageNumber": vm.stepFour.data.undercarriageNumber,
                    "price": 0,
                    "risks": roadsideRisks
                }
            return roadsideAssistanceInsuranceDTO;
        }

        vm.showPriceForInternational = function (ev) {
            var internationalTravelInsuranceDTO = createInternationalTravelInsuranceDTO();
            console.log(internationalTravelInsuranceDTO);
            var insurancePolicyDTO =
                {
                    "totalPrice": 1,
                    "dateOfIssue": vm.todayDate,
                    "dateBecomeEffective": vm.stepOne.data.fromDate,
                    "customers": null,
                    "internationalTravelInsurance": internationalTravelInsuranceDTO,
                    "homeInsurance": null,
                    "roadsideAssistanceInsurance": null
                }
            console.log(insurancePolicyDTO);
            insuranceService.getPrice(insurancePolicyDTO).then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        var prices = response.data;
                        $mdDialog.show(
                            $mdDialog.alert()
                                .parent(angular.element(document.body))
                                .clickOutsideToClose(true)
                                .title('Current Price: ' + prices[0] + '$')
                                .textContent('*Only for International Travel Insurance.')
                                .ariaLabel('Alert Dialog Demo')
                                .ok('OK')
                                .targetEvent(ev)
                        );
                    }
                });
        };

        vm.showPriceForHome = function (ev) {
            var internationalTravelInsuranceDTO = createInternationalTravelInsuranceDTO();
            console.log(internationalTravelInsuranceDTO);
            var homeInsuranceDTO = createHomeInsuranceDTO();
            console.log(homeInsuranceDTO);
            var insurancePolicyDTO =
                {
                    "totalPrice": 1,
                    "dateOfIssue": vm.todayDate,
                    "dateBecomeEffective": vm.stepOne.data.fromDate,
                    "customers": null,
                    "internationalTravelInsurance": internationalTravelInsuranceDTO,
                    "homeInsurance": homeInsuranceDTO,
                    "roadsideAssistanceInsurance": null
                }
            console.log(insurancePolicyDTO);
            insuranceService.getPrice(insurancePolicyDTO).then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        var prices = response.data;
                        var totalPrice = prices[0] + prices[1];
                        $mdDialog.show(
                            $mdDialog.alert()
                                .parent(angular.element(document.body))
                                .clickOutsideToClose(true)
                                .title('Current Price: ' + totalPrice + '$')
                                .htmlContent("<p>*International Travel Insurance: " + prices[0] + "$<p>" +
                                    "<p>*Home Insurance: " + prices[1] + "$<p>")
                                .ariaLabel('Alert Dialog Demo')
                                .ok('OK')
                                .targetEvent(ev)
                        );

                    }
                });
        };

        vm.showPriceForCar = function (ev) {
            var internationalTravelInsuranceDTO = createInternationalTravelInsuranceDTO();
            console.log(internationalTravelInsuranceDTO);
            var roadsideAssistanceInsuranceDTO = createRoadsideAssistanceInsuranceDTO();
            console.log(roadsideAssistanceInsuranceDTO);
            var insurancePolicyDTO =
                {
                    "totalPrice": 1,
                    "dateOfIssue": vm.todayDate,
                    "dateBecomeEffective": vm.stepOne.data.fromDate,
                    "customers": null,
                    "internationalTravelInsurance": internationalTravelInsuranceDTO,
                    "homeInsurance": null,
                    "roadsideAssistanceInsurance": roadsideAssistanceInsuranceDTO
                }
            console.log(insurancePolicyDTO);
            insuranceService.getPrice(insurancePolicyDTO).then(
                function (response) {
                    if (response.status == 200) {
                        console.log(response.data);
                        var prices = response.data;
                        var totalPrice = prices[0] + prices[2];
                        $mdDialog.show(
                            $mdDialog.alert()
                                .parent(angular.element(document.body))
                                .clickOutsideToClose(true)
                                .title('Current Price: ' + totalPrice + '$')
                                .htmlContent("<p>*International Travel Insurance: " + prices[0] + "$<p>" +
                                    "<p>*Roadside Assistance Insurance: " + prices[2] + "$<p>")
                                .ariaLabel('Alert Dialog Demo')
                                .ok('OK')
                                .targetEvent(ev)
                        );

                    }
                });
        };

        vm.cancel = function cancel() {
            $state.go('homePage');
        }

        vm.skip = function skip(whatIsSkipped) {
            if (whatIsSkipped === 'home') {
                vm.stepThree.isSkiped = true;
            } else {
                vm.stepFour.isSkiped = true;
                vm.getPrice();//if click skip on car insurance, show bill
            }
            vm.selectedStep = vm.selectedStep + 1;
        };

        vm.todayDate = new Date();

        vm.isDateValid = function (date) {
            var today = new Date();
            today.setHours(0, 0, 0, 0);
            var d = new Date(date);
            d.setHours(0, 0, 0, 0);
            return d >= today;
        };

        vm.addTabs = function () {
            vm.tabs = [];
            var totalPeople = sumNumberOfPeople();
            if (totalPeople < 1) {
                ngNotify.set('Total number of travelers must be greater then 0.', {
                    type: 'info'
                });
            }
            for (var i = 1; i <= totalPeople; i++) {
                vm.tabs.push("" + i);
            }
        }

        function sumNumberOfPeople() {
            var people = vm.stepOne.data.numberOfPeople;
            var totalPeople = 0;
            for (var num in people) {
                totalPeople += people[num];
            }
            return totalPeople;
        }

        function createPatternOfDate(date) {
            var pattern = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
            return pattern;
        }

        vm.createInsurancePolicy = function () {

            var customers = [];
            var customer = {};
            var customerData = vm.stepTwo.data;
            for (var c in customerData) {
                if (customerData[c] != null) {
                    if (!customerData[c].carrier) {
                        customerData[c].email = null;
                    }
                    customer = {
                        "firstName": customerData[c].firstname,
                        "lastName": customerData[c].lastname,
                        "personalId": customerData[c].personalId,
                        "passport": customerData[c].passport,
                        "address": customerData[c].address,
                        "telephoneNumber": customerData[c].telephoneNumber,
                        "carrier": customerData[c].carrier,
                        "email": customerData[c].email
                    }
                    customers.push(customer);
                }
            }
            console.log(customers);

            var internationalTravelInsuranceDTO = createInternationalTravelInsuranceDTO();
            internationalTravelInsuranceDTO.price = vm.prices[0];
            console.log(internationalTravelInsuranceDTO);

            var homeInsuranceDTO = null;
            if (!vm.stepThree.isSkiped) {
                homeInsuranceDTO = createHomeInsuranceDTO();
                homeInsuranceDTO.price = vm.prices[1];
            }
            console.log(homeInsuranceDTO);

            var roadsideAssistanceInsuranceDTO = null;
            if (!vm.stepFour.isSkiped) {
                roadsideAssistanceInsuranceDTO = createRoadsideAssistanceInsuranceDTO();
                roadsideAssistanceInsuranceDTO.price = vm.prices[2];
            }
            console.log(roadsideAssistanceInsuranceDTO);

            var insurancePolicyDTO =
                {
                    "totalPrice": vm.totalPrice,
                    "dateOfIssue": vm.todayDate,
                    "dateBecomeEffective": vm.stepOne.data.fromDate,
                    "customers": customers,
                    "internationalTravelInsurance": internationalTravelInsuranceDTO,
                    "homeInsurance": homeInsuranceDTO,
                    "roadsideAssistanceInsurance": roadsideAssistanceInsuranceDTO
                }
            insuranceService.createInsurancePolicy(insurancePolicyDTO).then(
                function (response) {
                    if (response.status == 200) {
                        toastr.success("Know you have your umbrella.", '<i>Success</i>');
                        console.log(response.data);
                        var paymentType = {};
                        paymentType.label = vm.pay;

                        var transactionDTO = {
                            "timestamp": "",

                            "paymentType": paymentType,
                            "amount": response.data.totalPrice,
                            "insurancePolicy": response.data
                        };

                        insuranceService
                            .createInquiry(transactionDTO)
                            .then(
                                function (response) {
                                    window.location = response.data.paymentUrl;

                                });
                    }
                    else {
                        toastr.error("Something got wrong with policy. Try again.", '<i>Error</i>');
                    }
                }

//                function(response) {
//					if (response.status == 200) {
//						ngNotify
//								.set(
//										'Know you have your umbrella.',
//										{
//											type : 'success'
//										});
//						console.log(response.data);
//
//						var paymentType = {};
//						paymentType.label = vm.pay;
//
//						var transactionDTO = {
//							"timestamp" : "",
//
//							"paymentType" : paymentType,
//							"amount" : response.data.totalPrice,
//							"insurancePolicy" : response.data
//						};
//
//						insuranceService
//								.createInquiry(transactionDTO)
//								.then(
//										function(response) {
//											window.location = response.data.paymentUrl;
//
//										});
//
//					}
//				}

            )
        }
    });
}());