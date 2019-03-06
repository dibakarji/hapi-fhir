package ca.uhn.fhir.jpa.provider.r4;

import ca.uhn.fhir.jpa.dao.FulltextSearchSvcImpl.Suggestion;
import ca.uhn.fhir.jpa.dao.IFhirSystemDao;
import ca.uhn.fhir.jpa.dao.IFulltextSearchSvc;
import ca.uhn.fhir.jpa.provider.BaseJpaSystemProviderDstu2Plus;
import ca.uhn.fhir.jpa.util.JpaConstants;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Parameters.ParametersParameterComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/*
 * #%L
 * HAPI FHIR JPA Server
 * %%
 * Copyright (C) 2014 - 2019 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

public class JpaSystemProviderR4 extends BaseJpaSystemProviderDstu2Plus<Bundle, Meta> {

	@Autowired()
	@Qualifier("mySystemDaoR4")
	private IFhirSystemDao<Bundle, Meta> mySystemDao;

	@Autowired(required = false)
	private IFulltextSearchSvc mySearchDao;

	@Operation(name = JpaConstants.OPERATION_EXPUNGE, idempotent = false, returnParameters = {
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_OUT_PARAM_EXPUNGE_COUNT, type = IntegerType.class)
	})
	public Parameters expunge(
		@IdParam IIdType theIdParam,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_LIMIT) IntegerType theLimit,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_DELETED_RESOURCES) BooleanType theExpungeDeletedResources,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_PREVIOUS_VERSIONS) BooleanType theExpungeOldVersions,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_EVERYTHING) BooleanType theExpungeEverything
	) {
		return super.doExpunge(theLimit, theExpungeDeletedResources, theExpungeOldVersions, theExpungeEverything);
	}

	@Operation(name = JpaConstants.OPERATION_EXPUNGE, idempotent = false, returnParameters = {
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_OUT_PARAM_EXPUNGE_COUNT, type = IntegerType.class)
	})
	public Parameters expunge(
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_LIMIT) IntegerType theLimit,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_DELETED_RESOURCES) BooleanType theExpungeDeletedResources,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_PREVIOUS_VERSIONS) BooleanType theExpungeOldVersions,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_EVERYTHING) BooleanType theExpungeEverything
	) {
		return super.doExpunge(theLimit, theExpungeDeletedResources, theExpungeOldVersions, theExpungeEverything);
	}

	// This is generated by hand:
	// ls hapi-fhir-structures-dstu2/target/generated-sources/tinder/ca/uhn/fhir/model/dstu2/resource/ | sort | sed "s/.java//" | sed "s/^/@OperationParam(name=\"/" | sed "s/$/\", type=IntegerType.class, min=0, max=1),/"
	@Operation(name = JpaConstants.OPERATION_GET_RESOURCE_COUNTS, idempotent = true, returnParameters = {
		@OperationParam(name = "AllergyIntolerance", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Appointment", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "AppointmentResponse", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "AuditEvent", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Basic", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Binary", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "BodySite", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Bundle", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "CarePlan", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "CarePlan2", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Claim", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ClaimResponse", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ClinicalImpression", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Communication", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "CommunicationRequest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Composition", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ConceptMap", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Condition", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Conformance", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Contract", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Contraindication", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Coverage", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DataElement", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Device", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DeviceComponent", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DeviceMetric", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DeviceUseRequest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DeviceUseStatement", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DiagnosticOrder", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DiagnosticReport", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DocumentManifest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "DocumentReference", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "EligibilityRequest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "EligibilityResponse", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Encounter", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "EnrollmentRequest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "EnrollmentResponse", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "EpisodeOfCare", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ExplanationOfBenefit", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "FamilyMemberHistory", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Flag", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Goal", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Group", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "HealthcareService", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ImagingObjectSelection", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ImagingStudy", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Immunization", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ImmunizationRecommendation", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ListResource", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Location", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Media", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Medication", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "MedicationAdministration", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "MedicationDispense", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "MedicationPrescription", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "MedicationStatement", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "MessageHeader", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "NamingSystem", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "NutritionOrder", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Observation", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "OperationDefinition", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "OperationOutcome", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Order", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "OrderResponse", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Organization", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Parameters", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Patient", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "PaymentNotice", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "PaymentReconciliation", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Person", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Practitioner", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Procedure", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ProcedureRequest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ProcessRequest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ProcessResponse", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Provenance", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Questionnaire", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "QuestionnaireAnswers", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ReferralRequest", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "RelatedPerson", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "RiskAssessment", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Schedule", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "SearchParameter", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Slot", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Specimen", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "StructureDefinition", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Subscription", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Substance", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "Supply", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "ValueSet", type = IntegerType.class, min = 0, max = 1),
		@OperationParam(name = "VisionPrescription", type = IntegerType.class, min = 0, max = 1)
	})
	@Description(shortDefinition = "Provides the number of resources currently stored on the server, broken down by resource type")
	public Parameters getResourceCounts() {
		Parameters retVal = new Parameters();

		Map<String, Long> counts = mySystemDao.getResourceCountsFromCache();
		counts = defaultIfNull(counts, Collections.emptyMap());
		counts = new TreeMap<>(counts);
		for (Entry<String, Long> nextEntry : counts.entrySet()) {
			retVal.addParameter().setName((nextEntry.getKey())).setValue(new IntegerType(nextEntry.getValue().intValue()));
		}

		return retVal;
	}

	@Operation(name = JpaConstants.OPERATION_META, idempotent = true, returnParameters = {
		@OperationParam(name = "return", type = Meta.class)
	})
	public Parameters meta(RequestDetails theRequestDetails) {
		Parameters parameters = new Parameters();
		parameters.addParameter().setName("return").setValue(getDao().metaGetOperation(theRequestDetails));
		return parameters;
	}

	@Operation(name = JpaConstants.OPERATION_SUGGEST_KEYWORDS, idempotent = true)
	public Parameters suggestKeywords(
		@OperationParam(name = "context", min = 1, max = 1) String theContext,
		@OperationParam(name = "searchParam", min = 1, max = 1) String theSearchParam,
		@OperationParam(name = "text", min = 1, max = 1) String theText
	) {
		ca.uhn.fhir.jpa.provider.dstu3.JpaSystemProviderDstu3.validateFulltextSearchEnabled(mySearchDao);

		if (isBlank(theContext)) {
			throw new InvalidRequestException("Parameter 'context' must be provided");
		}
		if (isBlank(theSearchParam)) {
			throw new InvalidRequestException("Parameter 'searchParam' must be provided");
		}
		if (isBlank(theText)) {
			throw new InvalidRequestException("Parameter 'text' must be provided");
		}

		List<Suggestion> keywords = mySearchDao.suggestKeywords(theContext, theSearchParam, theText);

		Parameters retVal = new Parameters();
		for (Suggestion next : keywords) {
			//@formatter:off
			retVal.addParameter()
				.addPart(new ParametersParameterComponent().setName("keyword").setValue(new StringType(next.getTerm())))
				.addPart(new ParametersParameterComponent().setName("score").setValue(new DecimalType(next.getScore())));
			//@formatter:on
		}

		return retVal;
	}

	/**
	 * /$process-message
	 */
	@Operation(name = JpaConstants.OPERATION_PROCESS_MESSAGE, idempotent = false)
	public IBaseBundle processMessage(
		HttpServletRequest theServletRequest,
		RequestDetails theRequestDetails,

		@OperationParam(name = "content", min = 1, max = 1)
		@Description(formalDefinition = "The message to process (or, if using asynchronous messaging, it may be a response message to accept)")
			Bundle theMessageToProcess
	) {

		startRequest(theServletRequest);
		try {
			return getDao().processMessage(theRequestDetails, theMessageToProcess);
		} finally {
			endRequest(theServletRequest);
		}

	}

	@Transaction
	public Bundle transaction(RequestDetails theRequestDetails, @TransactionParam Bundle theResources) {
		startRequest(((ServletRequestDetails) theRequestDetails).getServletRequest());
		try {
			return getDao().transaction(theRequestDetails, theResources);
		} finally {
			endRequest(((ServletRequestDetails) theRequestDetails).getServletRequest());
		}
	}

}
