package contracts

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    name = "Addition API contract"
    request {
        url = url("/api/addition/")
        method = POST
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(mapOf(
                "op1" to 1,
                "op2" to 2
        ))
    }
    response {
        status = OK
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(mapOf(
                "result" to 3
        ))
    }
}