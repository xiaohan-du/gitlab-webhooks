# Gitlab-webhooks

## Run Ngrok to generate a public URL for localhost:
`ngrok http 8080`

After running above command in Ngrok, get a forwarding URL such as:
https://27f9-2a00-23c7-21b3-f301-30af-9ec2-5297-c2d.ngrok-free.app

Add a webhook using the above URL + `/api/webhook`

e.g. https://27f9-2a00-23c7-21b3-f301-30af-9ec2-5297-c2d.ngrok-free.app/api/webhook
