package com.example.gitlabwebhooks.api;

import com.example.gitlabwebhooks.api.json.WebhookJson;
import com.example.gitlabwebhooks.api.json.WebhookJsonAssembler;
import com.example.gitlabwebhooks.service.WebhookAssembler;
import com.example.gitlabwebhooks.service.WebhookDto;
import com.example.gitlabwebhooks.service.WebhookService;
import com.example.gitlabwebhooks.service.message.WebhookRequest;
import com.example.gitlabwebhooks.service.message.WebhookResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/webhook")
public class WebhookController {
    private WebhookService webhookService;
    public WebhookController(WebhookService webhookService) { this.webhookService = webhookService; };
    @PostMapping
    public ResponseEntity<String> print(@RequestBody String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        WebhookDto webhookDTO = new WebhookDto(
                jsonObj.getString("object_kind"),
                jsonObj.getString("event_name"),
                jsonObj.getString("before"),
                jsonObj.getString("after"),
                jsonObj.getString("ref"),
                jsonObj.isNull("checkout_sha") ? null : jsonObj.getString("checkout_sha"),
                jsonObj.getInt("user_id"),
                jsonObj.getString("user_username"),
                jsonObj.getInt("project_id"),
                jsonObj.getInt("total_commits_count")
        );
        webhookService.saveWebhookData(webhookDTO);
        return new ResponseEntity<>(requestBody, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<WebhookJson>> getWebhookData() {
        WebhookRequest webhookRequest = WebhookRequest.of().build();
        WebhookResponse webhookResponse = webhookService.getWebhookData(webhookRequest);
        return ResponseEntity.ok(WebhookJsonAssembler.toWebhookJsonList(webhookResponse.getWebhookData()));
    }
}
