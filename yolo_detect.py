import subprocess
import re

def yolo_detect(image_path):
    # YOLOv5 감지 명령어를 구성합니다
    command = [
        'python', './yolov5/detect.py',
        '--weights', './yolov5/best.pt',
        '--img', '640',
        '--conf', '0.25',
        '--source', image_path
    ]
    
    # 명령어를 실행합니다
    result = subprocess.run(command, capture_output=True, text=True)

    # 명령어의 전체 출력 확인
    result_str = str(result)

    # 명령어의 출력 결과를 확인할 수 있습니다
    print("명령어 전체 출력:", result_str)
    
    # 명령어가 성공적으로 실행되었는지 확인합니다
    if result.returncode == 0:
        print("명령어 실행 성공")
    else:
        print("명령어 실행 실패")
        return None

    # 'Results saved to' 이후의 경로를 추출
    match = re.search(r'Results saved to (.+)', result_str)
    if match:
        results_path = match.group(1).strip()
        print("결과 디렉토리 경로:", results_path)
    else:
        print("결과 디렉토리 경로를 찾을 수 없습니다.")
        return None

    # 약물 정보 추출
    drug_info = re.findall(r'\d+ [가-힣]+', result_str)
    
    if drug_info:
        print("추출된 약물 정보:")
        for drug in drug_info:
            print(drug)
    else:
        print("약물 정보를 찾을 수 없습니다.")

    return results_path[7:-11], drug_info

# 함수 호출 예시
image_path = './yolo_test/val4.png'
a = yolo_detect(image_path)
print(a)
