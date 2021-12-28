package contracts

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    name = "Addition API contract"
    request {
        url = url("/api/addition/1/2")
        method = POST
    }
    response {
        status = OK
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(mapOf(
                "value" to 3
        ))
    }
}