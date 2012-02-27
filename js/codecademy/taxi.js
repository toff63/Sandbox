var nightSurcharge = function(hourOfDay){
  return (hourOfDay >= 20 || hourOfDay < 6) ? 0.5 : 0;
};
// add a parameter called hourOfDay to the function
var taxiFare = function (milesTraveled, hourOfDay) {
	var baseFare = 2.50;
	var costPerMile = 2.00;

	var cost = baseFare + (costPerMile * milesTraveled) + nightSurcharge(hourOfDay);
	return cost;
}
print(nightSurcharge(7));
print(taxiFare(2, 7));
