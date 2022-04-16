package service.mapper;

import model.Message;
import model.dto.responce.MessageResponseDto;
import service.UserService;
import service.impl.UserServiceImpl;

public class MessageMapper {
    private final UserService userService = new UserServiceImpl();

    public MessageResponseDto mapToDto(Message message) {
        MessageResponseDto messageResponseDto = new MessageResponseDto();
        messageResponseDto.setContent(message.getContent());
        messageResponseDto.setDataTime(message.getDataTime());
        messageResponseDto.setUsername(userService.get(message.getUserId()).getUsername());
        return messageResponseDto;
    }
}
