<#include "header.ftl">

<b>Welcome to our little demonstration on the VR Webapp</b><br><br>

<form method="POST" action="staffmembergui?page=insertOffer">
	<fieldset id="insertoffer">
		<legend>Required Information</legend>
		<div>
			<label>Start Time</label>
			<input type="text" name="startTime" id="datepicker1">
	    </div>

		<div>
			<label>End Time</label>
			<input type="text" name="endTime" id="datepicker2">
	    </div>

		<div>
			<label>Street</label>
			<input type="text" name="street">
	    </div>
	    
	    <div>
			<label>Town</label>
			<input type="text" name="town">
	    </div>

		<div>
			<label>Capacity</label>
			<input type="text" name="capacity">
	    </div>

		<div>
			<label>Fee</label>
			<input type="text" name="fee">
	    </div>
	</fieldset>
	<button type="submit" id="submit">Submit</button>
</form>
<#include "footer.ftl">