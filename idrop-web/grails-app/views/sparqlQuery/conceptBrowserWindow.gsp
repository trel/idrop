<%--<div id="conceptBrowserMain" class="container-fluid">--%>
<div id="conceptBrowser" class="container-fluid">
<div class="alert alert-info" style="margin-left:68px;width:1015px;">
	<h3>Pick up ${index}!</h3>
	You can navigate around the concept browser and pick up term to form your Sparql Query!
</div>
<div class="row-fluid " id="conceptBrowserVocabularyToolbar">
		<div class="span2" style="margin-left:68px;">
			<div class="btn-group pad-around">
				<a href="#myModal" role="button" class="btn btn-primary" data-toggle="modal"
						id="showSelectionListing" onclick="processShowSelectionListing()" >
						<g:message code="text.choose.vocabularies" />
				</a>
				<button type="button" class="btn btn-primary" id="backToTop"
					onclick="processVocabularySelection('','${index}')">
					<g:message code="text.back.to.top" />
				</button>
			</div>
		</div>
		
		<div class="offset1 span8">
			<div class="btn-toolbar">
			<div class="btn-group" data-toggle="buttons-radio">
				<g:each in="${hiveQueryState.selectedVocabularies}"
					var="selectedVocabulary">
					<div class="btn-group pad-around">
						<g:if test="${currentVocab.equals(selectedVocabulary)}">
							<button type="button" class="btn btn-mini btn-warning vocab-btn"
								id="${selectedVocabulary}"
								onclick="processVocabularySelection('${selectedVocabulary}','${index}')">
								${selectedVocabulary}
							</button>
						</g:if>
						<g:else>
							<button type="button" class="btn btn-mini btn-inverse btn-vocab"
								id="${selectedVocabulary}"
								onclick="processVocabularySelection('${selectedVocabulary}','${index}')">
								${selectedVocabulary}
							</button>
						</g:else>
					</div>
				</g:each>
			</div>
			</div>
		</div>
	</div>			
	<div class="container-fluid">
		<div class="row-fluid ">
			<div class="span12" id="searchConceptResults"></div>
		</div>
	</div>
	<div id="conceptBrowserPivotContainer">
		<g:render template="/sparqlQuery/conceptBrowserPivotView" />
	</div>
</div>

<div id="myModal" class="modal hide fade" role="dialog" tabindex="2000" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
			<h3 id="myModalLabel">Vocabulary List</h3>
	</div>
	<div class="modal-body">
		<form name="hiveQueryVocabularyForm" id="hiveQueryVocabularyForm">
			<table cellspacing="0" cellpadding="0" border="0" id="hiveVocabTable"
							class="table table-striped table-hover table-bordered" style="width:60%; margin-left:auto; margin-right: auto">
				<%--<g:hiddenField name="absPath" id="absPath" value="${absPath	}"/>
					--%><thead>
						<tr>
							<th><g:message code="text.select" /></th>
							<th><g:message code="text.vocabulary" /></th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${vocabs}" var="vocab">
							<tr id="${vocab.vocabularyName}">
								<td><g:checkBox name="selectedVocab" value="${vocab.vocabularyName}"
													checked="${vocab.selected}" /></td>
								<td>
									${vocab.vocabularyName}
								</td>
							</tr>
						</g:each>
					</tbody>
			</table>
		</form>
	
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary" data-dismiss="modal" onclick="selectVocabularies()"><g:message code="text.update" /></button>
	</div>
</div>
