<#include "header.ftl">

<b>Welcome to our little demonstration on the VR Webapp</b><br><br>

<form method="POST" action="guestgui?page=bookHolidayOffer">
	<fieldset id="browseAvailableOffers">
		<legend>Required Information</legend>
		<div>
			<label>Start Time</label>
			<input type="text" name="arrivalTime" id="datepicker1">
	    </div>

		<div>
			<label>End Time</label>
			<input type="text" name="departureTime" id="datepicker2">
	    </div>
		<div>
			<label>Persons</label>
			<input type="text" name="persons">
	    </div>
	    <div>
	    	<label>Guest name</label>
	    	<input type="text" name="name">
	    </div>
	    <div>
	    	<label>Guest email</label>
	    	<input type="text" name="email">
	    </div>
	</fieldset>
	<input type="hidden" value="${hid}" name="hid">
	<button type="submit" id="submit">Submit</button>
</form>
<#include "footer.ftl">