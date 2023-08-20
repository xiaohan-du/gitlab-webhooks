package com.example.gitlabwebhooks.api;

import com.example.gitlabwebhooks.api.json.WebhookJson;
import com.example.gitlabwebhooks.api.json.WebhookJsonAssembler;
import com.example.gitlabwebhooks.domain.ObjectAttributes;
import com.example.gitlabwebhooks.domain.Project;
import com.example.gitlabwebhooks.service.WebhookAssembler;
import com.example.gitlabwebhooks.service.WebhookDto;
import com.example.gitlabwebhooks.service.WebhookService;
import com.example.gitlabwebhooks.service.message.WebhookRequest;
import com.example.gitlabwebhooks.service.message.WebhookResponse;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/webhook")
public class WebhookController {
    private WebhookService webhookService;
    public WebhookController(WebhookService webhookService) { this.webhookService = webhookService; };
    @PostMapping
    public ResponseEntity<String> print(@RequestBody String requestBody) {
        JSONObject jsonObj = new JSONObject(requestBody);
        String timestamp = Instant.now().toString();
        Optional<JSONObject> projectObj = Optional.ofNullable(jsonObj.getJSONObject("project"));
        Optional<JSONObject> objectAttributesObj = Optional.ofNullable(jsonObj.optJSONObject("object_attributes"));
        Project project = projectObj.map(obj -> new Project(
                obj.optInt("id"),
                obj.optString("homepage", ""),
                obj.optString("default_branch", ""),
                obj.optString("name", ""),
                obj.optString("ref", ""),
                obj.optString("description", ""),
                obj.optString("web_url", ""),
                obj.optString("git_ssh_url", ""),
                obj.optString("git_http_url", ""),
                obj.optInt("visibility_level"),
                obj.optString("url", ""),
                obj.optString("ssh_url", ""),
                obj.optString("http_url", "")
        )).orElse(null);
        ObjectAttributes objectAttributes = objectAttributesObj.map(obj -> new ObjectAttributes(
                obj.optInt("id"),
                obj.optString("created_at", ""),
                obj.optString("updated_at", ""),
                obj.optString("description", ""),
                obj.optString("url", ""),
                obj.optInt("updated_by_id"),
                obj.optInt("author_id"),
                obj.optString("resolved_by_push", "")
        )).orElse(null);
        WebhookDto webhookDTO = new WebhookDto(
                1,
                jsonObj.getString("object_kind"),
                timestamp,
                project,
                objectAttributes
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
