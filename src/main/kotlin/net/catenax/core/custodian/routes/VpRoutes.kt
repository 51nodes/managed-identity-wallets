package net.catenax.core.custodian.routes

import com.danubetech.verifiablecredentials.jsonld.VerifiableCredentialContexts
import io.bkbn.kompendium.core.Notarized.notarizedPost
import io.bkbn.kompendium.core.metadata.RequestInfo
import io.bkbn.kompendium.core.metadata.ResponseInfo
import io.bkbn.kompendium.core.metadata.method.PostInfo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.catenax.core.custodian.models.ssi.LdProofDto
import net.catenax.core.custodian.models.ssi.VerifiableCredentialDto
import net.catenax.core.custodian.models.ssi.VerifiablePresentationDto
import net.catenax.core.custodian.models.ssi.VerifiablePresentationRequestDto

fun Route.vpRoutes() {

    route("/presentations") {

        route("/issue") {
            notarizedPost(
                PostInfo<Unit, VerifiablePresentationRequestDto, VerifiablePresentationDto>(
                    summary = "Issue Verifiable Presentation ",
                    description = "issue a verifiable Presentation",
                    requestInfo = RequestInfo(
                        description = "the verifiable Presentation dto",
                        examples = verifiablePresentationRequestDtoExample
                    ),
                    responseInfo = ResponseInfo(
                        status = HttpStatusCode.Created,
                        description = "The created Verifiable Presentation",
                        examples = verifiablePresentationDtoExample
                    ),
                    canThrow = setOf(invalidInputException, notFoundException),
                    tags = setOf("VerifiablePresentations")
                )
            ) {
                val verifiableCredentialDto = call.receive<VerifiableCredentialDto>()
                call.respond(
                    HttpStatusCode.Created,
                    verifiablePresentationDtoExample["demo"] as VerifiablePresentationDto
                )
            }
        }
    }
}

val verifiablePresentationRequestDtoExample = mapOf(
    "demo" to VerifiablePresentationRequestDto(
        context = listOf("https://www.w3.org/2018/credentials/v1"),
        type= listOf("VerifiablePresentation"),
        holder = "did:example:76e12ec712ebc6f1c221ebfeb1f",
        verifiableCredentialIds = listOf("http://example.edu/credentials/111, http://example.edu/credentials/222")
    )
)

val verifiablePresentationDtoExample = mapOf(
    "demo" to VerifiablePresentationDto(
        context = listOf("https://www.w3.org/2018/credentials/v1"),
        type= listOf("VerifiablePresentation"),
        holder = "did:example:76e12ec712ebc6f1c221ebfeb1f",
        verifiableCredentials = listOf(VerifiableCredentialDto(
            context = listOf(
                VerifiableCredentialContexts.JSONLD_CONTEXT_W3C_2018_CREDENTIALS_V1.toString(),
                VerifiableCredentialContexts.JSONLD_CONTEXT_W3C_2018_CREDENTIALS_EXAMPLES_V1.toString()
            ),
            id = "http://example.edu/credentials/3732",
            type = listOf("University-Degree-Credential, VerifiableCredential"),
            issuer = "did:example:76e12ec712ebc6f1c221ebfeb1f",
            issuanceDate = "2019-06-16T18:56:59Z",
            expirationDate = "2019-06-17T18:56:59Z",
            credentialSubject = mapOf("college" to "Test-University"),
            proof = LdProofDto(
                type = "Ed25519Signature2018",
                created = "2021-11-17T22:20:27Z",
                proofPurpose = "assertionMethod",
                verificationMethod = "did:example:76e12ec712ebc6f1c221ebfeb1f#keys-1",
                jws = "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFZERTQSJ9..JNerzfrK46Mq4XxYZEnY9xOK80xsEaWCLAHuZsFie1-NTJD17wWWENn_DAlA_OwxGF5dhxUJ05P6Dm8lcmF5Cg"
            )
        )),
        proof = LdProofDto(
            type = "Ed25519Signature2018",
            created = "2021-11-17T22:20:27Z",
            proofPurpose = "assertionMethod",
            verificationMethod = "did:example:76e12ec712ebc6f1c221ebfeb1f#keys-1",
            jws = "eyJiNjQiOmZhbHNlLCJjcml0IjpbImI2NCJdLCJhbGciOiJFZERTQSJ9..JNerzfrK46Mq4XxYZEnY9xOK80xsEaWCLAHuZsFie1-NTJD17wWWENn_DAlA_OwxGF5dhxUJ05P6Dm8lcmF5Cg"
        )
    )
)
