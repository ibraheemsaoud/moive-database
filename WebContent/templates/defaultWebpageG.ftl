<#include "header.ftl">

<b>Welcome to our little demonstration on the VR Webapp</b><br><br>

<form method="POST" action="guestgui?page=browseAvailableHolidayOffers">
	<fieldset id="browseAvailableOffers">
		<legend>Required Information</legend>
		<div>
			<label>Arrival Time</label>
			<input type="text" name="arrivalTime" id="datepicker1">
	    </div>

		<div>
			<label>Departure Time</label>
			<input type="text" name="departureTime" id="datepicker2">
	    </div>

		<div>
			<label>Persons</label>
			<input type="text" name="persons">
	    </div>
	</fieldset>
	<input type="submit" id="submit" name="SelectHOWebpage" value="Submit">
</form>
<#include "footer.ftl">