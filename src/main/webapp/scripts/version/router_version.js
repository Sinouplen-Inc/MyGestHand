'use strict';

mygesthandJhipsterApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
        $routeProvider
            .when('/version', {
                templateUrl: 'views/versions.html',
                controller: 'VersionController',
                resolve: {
                    resolvedVersion: ['Version', function (Version) {
                        return Version.query().$promise;
                    }]
                },
                access: {
                    authorizedRoles: [USER_ROLES.all]
                }
            })
    });
