# Tracking Number Generator API

## How to Run

1. Open Eclipse
2. Go to File > Import > Maven > Existing Maven Projects
3. Browse to this folder and select the project
4. Run `TrackingNumberApplication.java` as Sprint Boot Application

## API Example
PathVariable:
origin_country_id
destination_country_id
customer_id

Provide your origin_country_id , destination_country_id and customer_id. Based on these tracking number will create.
Example : origin_country_id=IN
          destination_country_id=MY
          customer_id=de619854-b59b-425e-9db4-943979e1bd49

`GET https://generate-tracking-number.onrender.com/api/next-tracking-number?origin_country_id=IN&destination_country_id=MY&customer_id=de619854-b59b-425e-9db4-943979e1bd49`

Response:
```json
{
  "tracking_number": "IM250521189T8001",
  "created_at": "2025-05-21T18:08:43.300030255Z"
}
```
