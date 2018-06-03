package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url 'https://ocean-rest-test.herokuapp.com/items'
        body("""
    {
      "description":"I am a simple description",
      "completed":true
    }
    """)
        headers {
            header('Content-Type', 'application/json')
        }
    }
    response {
        status 200
        body("""
  {
    "description": "INVALID",
    "rejectionReason": "Not completed"
  }
  """)
        headers {
            header('Content-Type': 'application/json')
        }
    }
}