app.factory('timeFactory', function () {
  var times = [ "01:00 PM", "01:30 PM", "02:00 PM"];

  var service = {}

  service.getTimes = function () {
    return times
  }

  return service;
})