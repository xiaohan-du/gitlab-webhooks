import { useState } from "react";

interface IModalState {
  openModal: boolean;
  setOpenModal: (openModal: boolean) => void;
  modalContent: string;
  setModalContent: (modalContent: string) => void;
}

export const useModalState = (): IModalState => {
  const [openModal, setOpenModal] = useState(false);
  const [modalContent, setModalContent] = useState('');
  return { openModal, setOpenModal, modalContent, setModalContent }
}
