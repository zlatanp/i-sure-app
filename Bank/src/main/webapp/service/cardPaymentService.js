/**
 * Created by Jasmina on 15/01/2018.
 */
app.service('cardPaymentService', function($http){
    return {
        getBankName: function (onSuccess, onError) {
            $http.get('/acquirer/name').then(onSuccess, onError);
        },
        getAmountForPayment: function (paymentId, onSuccess, onError) {
            $http.get('/acquirer/amount/' + paymentId).then(onSuccess, onError);
        },
        processOrder: function (paymentId, order, onSuccess, onError) {
            $http.post('/acquirer/order/' + paymentId, order).then(onSuccess, onError);
        },
        sendResponse: function (paymentResponseInfo, onSuccess, onError) {
            $http.post('/acquirer/checkout', paymentResponseInfo).then(onSuccess, onError);
        }
    }
});