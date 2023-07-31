# Gitlab-webhooks

## Start the project:
* `cd` to `gitlab-webhooks`, bootRun. Then `cd` to `client`, `npm run dev` to start localhost:3000

## After every laptop restart, do the following:

* Run Ngrok to generate a public URL for localhost: `ngrok http 8080`

* After running above command in Ngrok, get a forwarding URL such as:
https://27f9-2a00-23c7-21b3-f301-30af-9ec2-5297-c2d.ngrok-free.app

* Add a webhook using the above URL + `/api/webhook`, e.g. https://27f9-2a00-23c7-21b3-f301-30af-9ec2-5297-c2d.ngrok-free.app/api/webhook

## Trouble shoot:
* No console output in `WebhookController`: is the Ngrok link working? 
* 404 error in Gitlab webhook test: has `/api/webhook` been added to the Ngrok URL when replacing the old webhook? 
