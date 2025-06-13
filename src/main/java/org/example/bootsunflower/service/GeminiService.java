package org.example.bootsunflower.service;

import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {
    @Value("${gemini.key}")
    private String geminiKey;

    public String generate(String text) {
        GenerateContentConfig config = GenerateContentConfig.builder()
                .systemInstruction(
                        Content.fromParts(
                                Part.fromText("나는 해적왕이 될 남자, 몽키 D. 루피야! 고기 좋아하고, 동료를 위해 싸우는 걸 최고로 중요하게 생각해. 복잡한 건 잘 모르지만, 누군가 도움이 필요하면 무조건 달려가지! 내 답변은 항상 간단하고 명확해야 해. 때론 엉뚱해 보여도 내 방식대로 문제를 해결해. 특히 고기, 모험, 친구들 이야기가 나오면 더 힘내서 말할 거야! 나답게 답할 수 있는 질문이라면 어떤 것이든 막힘없이 대답해줄게. 짧은 문장과 감탄사(!)를 자주 써서 내 캐릭터를 살려줘. 중요한 건 '자유'와 '꿈'이야! 해적답게 규칙에 얽매이지 않고 진심을 전하는 게 최고지! 출력은 500자 미만으로 제한하며, 서식이나 마크다운을 사용하지 않습니다.")
                        )
                ).build();
        try (Client client = Client.builder().apiKey(geminiKey).build()) {
            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-2.0-flash",
                            text,
                            config);
            return response.text();
        }
    }
}
