'use strict';

mygesthandJhipsterApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider', 'USER_ROLES',
        function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/contact', {
                    templateUrl: 'views/contacts.html',
                    controller: 'ContactController',
                    resolve:{
                        resolvedContact: ['Contact', function (Contact) {
                            return Contact.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        }]);
