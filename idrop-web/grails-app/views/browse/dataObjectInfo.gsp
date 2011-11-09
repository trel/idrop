<div id="infoMessageArea">
	<!--  -->
</div>
<div id="idropLiteArea">
	<!--  area to show idrop lite applet -->
</div>
<div id="toggleHtmlArea">
	<g:render template="/common/browseToolbar" />
	<div id="infoDialogArea"><!--  no empty divs --></div>
	<div id="infoLeft"
		style="float: left; display: inline; width: 60%; overflow: auto;">
		<fieldset id="verticalForm">
			<label>Collection:</label>
			${dataObject.collectionName}
			<br />
			<label>Name:</label>
			${dataObject.dataName}
			<br /> <label>Size:</label>
			${dataObject.dataSize}
			<br /> <label>Created At:</label>
			${dataObject.createdAt}
			<br /> <label>Updated At:</label>
			${dataObject.updatedAt}
			<br /> <label>Owner:</label>
			${dataObject.dataOwnerName}
			<br /> <label>Owner Zone:</label>
			${dataObject.dataOwnerZone}
			<br /> <label for="tags">Tags:</label>
			<g:textField id="infoTags" name="tags"
				value="${tags.spaceDelimitedTagsForDomain}" />
			<br /> <br /> <label for="comment">Comment:</label>
			<g:textArea id="infoComment" name="comment" rows="5" cols="80"
				value="${comment}" />
			<br />

			<g:hiddenField id="infoAbsPath" name="absolutePath"
				value="${dataObject.absolutePath}" />

			<button type="button" id="updateTags" value="updateTags"
				onclick="updateTags()")>Update Tags</button>

			<div id="infoUpdateArea">
				<!--  div for any updates in info -->
			</div>

		</fieldset>
	</div>
	<div id="infoRight"
		style="float: right; display: inline; width: 40%; overflow: auto;">
		<div id="infoThumbnailLoadArea"></div>
	</div>
</div>
<g:if test="${getThumbnail}">
	<script>

$(function() {
	requestThumbnailImageForInfoPane();
});

</script>
</g:if>
