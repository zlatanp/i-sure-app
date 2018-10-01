/**
 * Created by Jasmina on 15/01/2018.
 */
app.controller('CardPaymentController', function ($scope, $state, $routeParams, $location, cardPaymentService){

    var months = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    var years = [2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028];

    $scope.months = months;
    $scope.years = years;
    $scope.paymentId = $routeParams.paymentId;
    $scope.showProgress = false;

    var loadName = function () {
        cardPaymentService.getBankName(function(response){
            $scope.bankName = response.data.name;
        });
    }

    var loadAmount = function () {
        var id = $scope.paymentId;
        cardPaymentService.getAmountForPayment(id, function(response){
            var amount = response.data.amount;
            amount = parseFloat(amount);
            $scope.amount = amount;
        });
    }

    loadName();
    loadAmount();


    $scope.pay = function () {
        $scope.showProgress = true;
        $scope.order.amount = $scope.amount;
        console.log($scope.order);
        cardPaymentService.processOrder($scope.paymentId, $scope.order, function(response){
            var paymentData = response.data;
            var paymentResponse = response;
            console.log(paymentResponse);
            if(paymentData.transactionStatus === "SUCCESSFUL" && paymentResponse.status == 200){
                $location.path("/acquirer/success");
                cardPaymentService.sendResponse(paymentData, function (response) {
                    console.log(response.data);
                });
            }else if((paymentData.transactionStatus === "FAILED"
                || paymentData.transactionStatus === "CARD_AUTH_FAILURE") && paymentResponse.status == 200) {
                console.log("Error");
                $location.path("/acquirer/error");
            }else {
                console.log("Fail communication");
                $location.path("/acquirer/fail");
            }
        });
    }

    $scope.cancel = function () {
        window.location = document.referrer;
    }
});