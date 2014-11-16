'use strict';

mygesthandJhipsterApp.controller('ContactController', function ($scope, resolvedContact, Contact, resolvedUser) {

    $scope.contacts = resolvedContact;
    $scope.users = resolvedUser;

    $scope.create = function () {
        Contact.save($scope.contact,
            function () {
                $scope.contacts = Contact.query();
                $('#saveContactModal').modal('hide');
                $scope.clear();
            });
    };

    $scope.update = function (id) {
        $scope.contact = Contact.get({id: id});
        $('#saveContactModal').modal('show');
    };

    $scope.delete = function (id) {
        Contact.delete({id: id},
            function () {
                $scope.contacts = Contact.query();
            });
    };

    $scope.clear = function () {
        $scope.contact = {
            birthday: null,
            nationality: null,
            birth_country: null,
            quality: null,
            age_range: null,
            mutation_indicator: null,
            nationality_nature: null,
            date_of_entry: null,
            date_of_recipt: null,
            date_of_expiry: null,
            date_of_qualification: null,
            license_status: null,
            type_of_application: null,
            license_number: null,
            sex: null,
            individual_accident_insurance: null,
            allows_trade_eamil: null,
            allows_address: null,
            civility: null,
            married_name: null,
            foreign_birth_city: null,
            french_birth_city: null,
            departement_birth: null,
            address_1: null,
            address_2: null,
            address_3: null,
            french_postal_code: null,
            french_city: null,
            foreign_postal_code: null,
            foreign_city: null,
            country: null,
            home_phone_number: null,
            cellular_phone_number: null,
            work_phone_number: null,
            fax_number: null,
            license_status_date: null,
            id: null
        };
    };
});
