<#include "header.ftl">

<b>Welcome to our little demonstration on the VR Webapp</b>

<table id="availableHOs">
	<tr>
		<th>#</th>
		<th>Street</th>
		<th>Town</th>
		<th>Capacity</th>
		<th>Ordering</th>
	</tr>
	<#list availableOffers as ho>
	<tr>
		<td>${ho.id}</td>
		<td>${ho.addressData.street}</td>
		<td>${ho.addressData.town}</td>
		<td>${ho.capacity}</td>
		<td><a href="guestgui?page=selectHolidayOffer&amp;hid=${ho.id}" title="Make Booking">Make Booking</a></td>
	</tr>
	</#list>
</table>
<#include "footer.ftl">