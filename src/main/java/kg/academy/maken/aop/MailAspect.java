package kg.academy.maken.aop;

import kg.academy.maken.model.card_model.CardMemberModel;
import kg.academy.maken.model.card_model.CardPostModel;
import kg.academy.maken.model.card_model.CardSetRatingModel;
import kg.academy.maken.model.commment_model.CommentModel;
import kg.academy.maken.model.user_model.UserTokenModel;
import kg.academy.maken.service.CardService;
import kg.academy.maken.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j

public class MailAspect {
    @Autowired
    private CardService cardService;
    @Autowired
    private MailService mailService;

    @Around(value = "@annotation(Mail)")
    public Object mailSender(ProceedingJoinPoint joinPoint) throws Throwable {
        Object methodResult = joinPoint.proceed();
        Object[] argument = joinPoint.getArgs();
        try  {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Mail mail = method.getAnnotation(Mail.class);

            switch (mail.message()) {
                case "comment":
                    CommentModel commentModel = (CommentModel) argument[0];
                    cardService.sendMail(commentModel);
                    break;
                case "user":
                    UserTokenModel userModel = (UserTokenModel) methodResult;
                    mailService.send(userModel.getEmail(), "Maken","https://maken-task.herokuapp.com/sign-in");
                    break;
                case "rating":
                    CardSetRatingModel cardSetRatingModel = (CardSetRatingModel) argument[0];
                    cardService.sendMail(cardSetRatingModel);
                    break;
                case "reject":
                    CardPostModel cardPostModel = (CardPostModel) argument[0];
                    cardService.sendMail(cardPostModel);
                    break;
                case "member":
                    CardMemberModel cardMemberModel = (CardMemberModel) argument[0];
                    cardService.sendMail(cardMemberModel);
                    break;
            }
        }catch (NullPointerException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return methodResult;
    }
}
