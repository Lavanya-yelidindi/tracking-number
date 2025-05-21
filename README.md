# Tracking Number Generator API

## How to Run

1. Open Eclipse
2. Go to File > Import > Maven > Existing Maven Projects
3. Browse to this folder and select the project
4. Run `TrackingNumberApiApplication.java` as Sprint Boot Application

## API Example
PathVariable:
origin_country_id
destination_country_id
created_at
customer_id

`GET https://generate-tracking-number.onrender.com/api/next-tracking-number?origin_country_id=IN&destination_country_id=MY&created_at=2024-05-20T15:30:12%2B08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49`

Response:
```json
{
  "tracking_number": "IM24052015300001",
  "created_at": "2025-05-21T16:23:01.228996565Z"
}
```
